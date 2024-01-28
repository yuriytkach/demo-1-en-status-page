package org.yuriytkach.demo;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

@Path("/")
@RequiredArgsConstructor
public class StatusPageController {

    public static final String DEFAULT_IP = "56.23.45.50";

    private final Template page;
    private final StatusService statusService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(
      @HeaderParam("X-Forwarded-For") final String xForwardedFor
    ) {
        final String ip = xForwardedFor == null ? DEFAULT_IP : xForwardedFor.split(",")[0];
        final var data = statusService.loadData(ip);
        return page.data("data", data);
    }

}
