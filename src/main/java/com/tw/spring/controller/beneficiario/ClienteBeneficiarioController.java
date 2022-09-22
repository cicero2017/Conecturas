package com.tw.spring.controller.beneficiario;

import com.tw.spring.controller.beneficiario.ClienteBeneficiarioResponse;
import com.tw.spring.service.ClienteBeneficiarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@Api(value = "beneficiario")
@RestController
@AllArgsConstructor
public class ClienteBeneficiarioController {

	
    private final ClienteBeneficiarioService service;

    @ApiOperation(value = "lista beneficiarios" )
    @GetMapping("/beneficiario/listar")
    public ResponseEntity<List<ClienteBeneficiarioResponse>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @ApiOperation(value = "lista beneficiarios por id " )
    @GetMapping("/beneficiario/{id}")
    public ResponseEntity<ClienteBeneficiarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @ApiOperation(value = " salvar  beneficiario " )
    @PostMapping("/beneficiario/salvar")
    public ResponseEntity<ClienteBeneficiarioResponse> salvar(@RequestBody @Valid ClienteBeneficiarioRequest request){
        return ResponseEntity.ok(service.salvar(request));
    }
        
    @ApiOperation(value = "deletar beneficiario por id " )
    @DeleteMapping("/beneficiario/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "atualizar beneficiarios por id " )
    @PutMapping("/beneficiario/{id}")
    public ResponseEntity<ClienteBeneficiarioResponse> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteBeneficiarioRequest request){
      return ResponseEntity.ok(service.atualizar(id, request));
    }
}
