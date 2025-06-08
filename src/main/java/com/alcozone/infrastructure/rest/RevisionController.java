package com.alcozone.infrastructure.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alcozone.application.usecase.revision.*;
import com.alcozone.infrastructure.dto.revision.request.CreateRevisionRequestDTO;

import com.alcozone.infrastructure.dto.revision.response.DefaultRevisionResponseDTO;
import com.alcozone.infrastructure.dto.revision.response.RevisionListItemDTO;
import com.alcozone.utils.CsvExportUtil;
import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.alcozone.infrastructure.dto.revision.request.GetRevisionRequestDTO;

@Path("/revision")
@Produces(MediaType.APPLICATION_JSON)
public class RevisionController {

    @Inject CreateRevisionUseCase createRevisionUseCase;
    @Inject GetRevisionUseCase getRevisionUseCase;
    @Inject CsvExportUtil csvExportUtil;
    @Inject ListRevisionsUseCase listRevisionsUseCase;
    @Inject DeleteRevisionUseCase deleteRevisionUseCase;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response saveRevision(@BeanParam CreateRevisionRequestDTO requestDTO) throws IOException {
        createRevisionUseCase.execute(requestDTO);
        return Response.accepted().build();
    }

    @DELETE
    @Path("/{uuid}")
    public Response deleteRevision(@PathParam("uuid") String uuid) {
        try{
            deleteRevisionUseCase.execute(uuid);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Revision deleted successfully");
            return Response.ok(response).build();
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to delete revision: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @GET
    public Response getRevision(@BeanParam GetRevisionRequestDTO requestDTO) {
        return Response.ok(getRevisionUseCase.execute(requestDTO)).build();
    }

    @GET
    @Path("/csv")
    @Produces("text/csv")
    public Response downloadRevisionCsv(@BeanParam GetRevisionRequestDTO requestDTO) {
        DefaultRevisionResponseDTO revision = getRevisionUseCase.execute(requestDTO);
        byte[] csvBytes = csvExportUtil.revisionToCsv(revision);  // CSV en bytes
        return Response.ok(csvBytes)
                .header("Content-Disposition", "attachment; filename=\"revision.csv\"")
                .type("application/octet-stream")
                .build();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRevisions() {
        List<RevisionListItemDTO> result = listRevisionsUseCase.execute();
        return Response.ok(result).build();
    }
}
