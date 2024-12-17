package org.mosaic.hub.application.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.mosaic.hub.application.dtos.HubPathResponse;
import org.mosaic.hub.domain.model.Hub;
import org.mosaic.hub.domain.model.HubTransfer;
import org.mosaic.hub.domain.repository.HubRepository;
import org.mosaic.hub.libs.exception.CustomException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HubShortPathService {

  private final HubRepository hubRepository;

  @Cacheable(value = "hubPathCache", key = "#departureHubUuid")
  public Map<String, List<HubPathResponse>> findShortestPaths(
      String departureHubUuid) {
    Hub departureHub = getHubByUuid(departureHubUuid);

    Map<String, String> hubNameMap = new HashMap<>();
    Map<String, List<HubTransfer>> graph = new HashMap<>();
    generateGraph(departureHub, hubNameMap, graph);

    return dijkstra(graph, hubNameMap, departureHubUuid);
  }

  private Map<String, List<HubPathResponse>> dijkstra(
      Map<String, List<HubTransfer>> graph,
      Map<String, String> hubNameMap,
      String departureHubUuid) {

    Map<String, HubTimeDistance> hubTimeDistanceMap = new HashMap<>();
    Map<String, String> history = new HashMap<>();
    PriorityQueue<HubNode> queue = new PriorityQueue<>(
        Comparator.comparingInt(HubNode::getTime));

    hubTimeDistanceMap.put(departureHubUuid, new HubTimeDistance(0, 0.0));
    queue.add(new HubNode(departureHubUuid, 0));

    while (!queue.isEmpty()) {
      HubNode current = queue.poll();

      if (current.getTime() > hubTimeDistanceMap.getOrDefault(
              current.getHubUuid(),
              new HubTimeDistance(Integer.MAX_VALUE, Double.MAX_VALUE))
          .getEstimatedTime()) {
        continue;
      }

      List<HubTransfer> hubTransfers = graph.get(current.getHubUuid());
      for (HubTransfer hubTransfer : hubTransfers) {
        int nextTime = hubTimeDistanceMap.get(current.getHubUuid())
            .getEstimatedTime() + hubTransfer.getEstimatedTime();

        double nextDistance = hubTimeDistanceMap.get(current.getHubUuid())
            .getEstimatedDistance() + hubTransfer.getEstimatedDistance();

        String nextHubUuid = hubTransfer.getArrivalHub().getUuid();
        if (nextTime < hubTimeDistanceMap.getOrDefault(
            nextHubUuid, new HubTimeDistance(Integer.MAX_VALUE, Double.MAX_VALUE))
            .getEstimatedTime()) {
          hubTimeDistanceMap.put(nextHubUuid, new HubTimeDistance(nextTime, nextDistance));
          history.put(nextHubUuid, current.getHubUuid());
          queue.add(new HubNode(nextHubUuid, nextTime));
        }
      }
    }

    return organizeShortestPaths(hubNameMap, hubTimeDistanceMap, history);
  }

  private Map<String, List<HubPathResponse>> organizeShortestPaths(
      Map<String, String> hubNameMap,
      Map<String, HubTimeDistance> hubTimeDistanceMap,
      Map<String, String> history) {

    Map<String, List<HubPathResponse>> shortestPaths = new HashMap<>();

    for (String arrivalHubUuid : hubNameMap.keySet()) {
      LinkedList<HubPathResponse> path = new LinkedList<>();
      for (String hubUuid = arrivalHubUuid; hubUuid != null;
          hubUuid = history.get(hubUuid)) {
        path.addFirst(HubPathResponse.create(
            hubUuid, hubNameMap.get(hubUuid),
            hubTimeDistanceMap.get(hubUuid).getEstimatedTime(),
            hubTimeDistanceMap.get(hubUuid).getEstimatedDistance()));
      }
      shortestPaths.put(arrivalHubUuid, path);
    }

    return shortestPaths;
  }

  private Hub getHubByUuid(String hubUuid) {
    return hubRepository.findByUuid(hubUuid).orElseThrow(
        () -> new CustomException(HttpStatus.NOT_FOUND, "존재하지 않는 허브입니다."));
  }

  private void generateGraph(
      Hub departureHub,
      Map<String, String> hubNameMap,
      Map<String, List<HubTransfer>> graph) {

    Queue<Hub> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();

    queue.add(departureHub);
    while (!queue.isEmpty()) {
      Hub currentHub = queue.poll();

      if (visited.contains(currentHub.getUuid())) {
        continue;
      }

      visited.add(currentHub.getUuid());
      hubNameMap.put(currentHub.getUuid(), currentHub.getName());
      graph.put(currentHub.getUuid(), currentHub.getHubTransfers());

      for (HubTransfer transfer : currentHub.getHubTransfers()) {
        Hub nextHub = transfer.getArrivalHub();
        if (!visited.contains(nextHub.getUuid())) {
          queue.add(nextHub);
        }
      }
    }
  }

  @Getter
  @AllArgsConstructor
  private static class HubTimeDistance {

    private int estimatedTime;
    private double estimatedDistance;
  }

  @Getter
  @AllArgsConstructor
  private static class HubNode {

    private String hubUuid;
    private int time;
  }
}