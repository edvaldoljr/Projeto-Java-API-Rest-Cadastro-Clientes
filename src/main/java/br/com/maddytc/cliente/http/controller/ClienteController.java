package br.com.maddytc.cliente.http.controller;

import br.com.maddytc.cliente.entity.Cliente;
import br.com.maddytc.cliente.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//Para dizermos que essa classe é nosso controller precisamos da seguinte anotação @RestController
//Depois precisamos definir nosso rota de clientes com a anotação @ResquestMapping.

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    //Vamos criar nosso primeiro método e vamos usar nosso ClienteService
    //Vamos injetar as dependencias com a anotação @Autowired
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    //Agora criando o primeiro método
    //Vamos utilizar o vervo @PostMapping
    @PostMapping
    //Cada requisição seja realizada com sucesso vamos retornar um Status @ResponseStatus com os parametro (HttpStatus.CREATED)
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente) {
        //Através do clienteService vamos salvar o cliente através do parametro
        return clienteService.salvar(cliente);
        //Mas para isso funcionar precisamos informar qual é o verbo http que vamos utilizar nesse método "Vamos utilizar o vervo @PostMapping"
    }

    //Nesse próximo método vamos retornar uma lista de clientes
    //Precisamos também declarar o verbo http @GetMapping @ResponseStatus(HttpStatus.OK) agora vamos ter a lista de clientes cadastrada na base
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listaCliente() {
        return clienteService.listaCliente();
    }

    //Agora vamos consultar um cliente por id
    //Como também é uma consulta iremos usar @GetMapping mas como o método retorna um id temos que informa no Path
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente buscarClientePorId(@PathVariable("id") Long id) {
        return clienteService.buscarId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado na base de dados! ")); //Caso o cliente não exista
    }

    //Método remover cliente
    //Verbo http @DeleteMapping
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //NO_CONTENTE sem conteudo
    public void removerCliente(@PathVariable("id") Long id) {
        clienteService.buscarId(id)
                .map(cliente -> {
                    clienteService.removerPorId(cliente.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado na base de dados! "));
    }

    //Método para atualização do cliente
    //Para atualização de cliente vamos utilizar o verbo @Put
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        clienteService.buscarId(id)
                .map(clienteBase -> {
                    /**
                     * vamos usar um recurso do model map para atualizar o cliente da base como
                     * ainda não temos o model map em nosso pom.xml vamos adicionar
                     * <dependency>
                     * 			<groupId>org.modelmapper</groupId>
                     * 			<artifactId>modelmapper</artifactId>
                     * 			<version>2.3.5</version>
                     * 		</dependency>
                     */
                    modelMapper.map(cliente, clienteBase);
                    clienteService.salvar(clienteBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado na base de dados! "));
    }
}
