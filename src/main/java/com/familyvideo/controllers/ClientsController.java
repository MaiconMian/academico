package com.familyvideo.controllers;

import com.familyvideo.DTOs.ClientDTO;
import com.familyvideo.model.service.Client;
import com.familyvideo.model.service.Addresses;
import com.familyvideo.repository.service.AddressesRepository;
import com.familyvideo.repository.service.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressesRepository addressesRepository;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO clientDTO) {

        Addresses address = new Addresses();

        address.setAddressStreet(clientDTO.getAddressStreet());
        address.setAddressNumber(clientDTO.getAddressNumber());
        address.setAddressPostalCode(clientDTO.getAddressPostalCode());
        address.setAddressCity(clientDTO.getAddressCity());
        address.setAddressCountry(clientDTO.getAddressCountry());

        addressesRepository.save(address);

        Client client = new Client();
        client.setClientName(clientDTO.getClientName());
        client.setClientLastName(clientDTO.getClientLastName());
        client.setAddress(address);

        clientRepository.save(client);

        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody ClientDTO clientDTO) {
        return clientRepository.findById(id).map(client -> {
            Addresses address = client.getAddress();
            address.setAddressStreet(clientDTO.getAddressStreet());
            address.setAddressNumber(clientDTO.getAddressNumber());
            address.setAddressPostalCode(clientDTO.getAddressPostalCode());
            address.setAddressCity(clientDTO.getAddressCity());
            address.setAddressCountry(clientDTO.getAddressCountry());

            client.setClientName(clientDTO.getClientName());
            client.setClientLastName(clientDTO.getClientLastName());

            addressesRepository.save(address);
            clientRepository.save(client);

            return ResponseEntity.ok(client);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return ResponseEntity.ok(clients);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Integer id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return ResponseEntity.ok("Client deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
    }
}
