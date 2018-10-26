package nl.kevinvanschaijk.rest.playlist;

import nl.kevinvanschaijk.service.entity.playlist.Playlist;
import nl.kevinvanschaijk.service.entity.track.Track;
import nl.kevinvanschaijk.service.services.playlist.PlaylistServiceImpl;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.apache.cxf.common.util.StringUtils.isEmpty;


@Path("/playlists")
public class PlaylistController {

    @Inject
    PlaylistServiceImpl playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        Response response = null;
        if (!isEmpty(token)) {
            try {
                response = Response.ok().entity(playlistService.getPlaylists(token)).build();
            } catch (AuthenticationException e) {
                response = Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return response;
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksInPlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        Response response = null;
        if (!isEmpty(token)) {
            try {
                response = Response.ok().entity(playlistService.getPlaylistTracks(id, token)).build();
            } catch (AuthenticationException e) {
                response = Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(Playlist playlist, @QueryParam("token") String token) {
        Response response = null;
        if (!isEmpty(token)) {
            try {
                response = Response.status(Response.Status.CREATED).entity(playlistService.addPlaylist(playlist, token)).build();
            } catch (AuthenticationException e) {
                response = Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return response;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        Response response = null;
        if (!isEmpty(token)) {
            try {
                response = Response.ok().entity(playlistService.deletePlaylist(id, token)).build();
            } catch (AuthenticationException e) {
                response = Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return response;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updatePlaylist(Playlist playlist, @PathParam("id") int id, @QueryParam("token") String token) {
        Response response = null;
        if (!isEmpty(token)) {
            try {
                response = Response.ok().entity(playlistService.updatePlaylist(playlist, id, token)).build();
            } catch (AuthenticationException e) {
                response = Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return response;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{playlistId}/tracks/{trackId}")
    public Response deletePlaylistTrack(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId, @QueryParam("token") String token) {
        Response response = null;
        if (!isEmpty(token)) {
            try {
                response = Response.ok().entity(playlistService.deletePlaylistTrack(playlistId, trackId, token)).build();
            } catch (AuthenticationException e) {
                response = Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{playlistId}/tracks")
    public Response addPlaylistTrack(Track track, @PathParam("playlistId") int playlistId, @QueryParam("token") String token) {
        Response response = null;
        if (!isEmpty(token)) {
            try {
                response = Response.status(Response.Status.CREATED).entity(playlistService.addPlaylistTrack(track, playlistId, token)).build();
            } catch (AuthenticationException e) {
                response = Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return response;
    }
}
