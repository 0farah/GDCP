package com.orange.portail.rest;


import com.orange.portail.dto.ConnectionDTO;
import com.orange.portail.service.ConnectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ConnectionResource {

    private final ConnectionService connectionService;

    public ConnectionResource(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }


    @GetMapping("")
    public ResponseEntity<Object> getConnection() {
        ConnectionDTO connectionDTO = connectionService.getConnections();
        return ResponseEntity.ok(connectionDTO);
    }
}
