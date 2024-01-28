package org.yuriytkach.demo;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@RegisterRestClient(configKey = "geoip-api")
public interface GeoIPClient {

  @GET
  @Path("/json/{ip}")
  GeoLocationData getGeoLocationData(
    @PathParam("ip") String ip
  );

  @RegisterForReflection
  record GeoLocationData(float lat, float lon, String city, String country) {}
}
