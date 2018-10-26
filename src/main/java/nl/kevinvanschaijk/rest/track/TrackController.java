package nl.kevinvanschaijk.rest.track;

import nl.kevinvanschaijk.service.services.track.TrackServiceImpl;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.apache.cxf.common.util.StringUtils.isEmpty;

@Path("/tracks")
public class TrackController {

    @Inject
    TrackServiceImpl trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(@QueryParam("forPlaylist") String forPlayList, @QueryParam("token") String token) {
        Response response = null;
        if (!isEmpty(token) && !isEmpty(forPlayList)) {
            try {
                response = Response.ok().entity(trackService.getAllTracks(forPlayList, token)).build();
            } catch (AuthenticationException e) {
                response = Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return response;
    }
}
