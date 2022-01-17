package br.com.maddytc.cliente.service;

import br.com.maddytc.cliente.entity.Cliente;
import br.com.maddytc.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * service servirá para adicionarmos nossas regras de negócio
 */

//Como é uma classe de serviço vamos fazer a seguinte anotação @Service

@Service
public class ClienteService {

    //Nessa classe vamos precisar do nosso repositório
    //Vamos anotar com @Autowired
    @Autowired
    private ClienteRepository clienteRepository;

    //Vamos criar nosso primeiro método Salvar que servirá para salvar um cliente como atualizar podemos verificar na implementação
    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    //Segundo método
    //Atraves do médoto findAll teremos uma lista de todos os clientes cadastrados na base de dados
    public List<Cliente> listaCliente(){
        return clienteRepository.findAll();
    }

    //Terceiro método vai retornar um Optional de Cliente e vamos o chamar de buscar por id
    public Optional<Cliente> buscarId(Long id) {
        return clienteRepository.findById(id);
    }

    //Quarto método esse método não irá retornar nenhum valor vai apenas servi vara excluir o id
    public void removerPorId(Long id) {
        clienteRepository.deleteById(id);
    }

    /**
     * Com tudo criado agora vamos desenvolver nossa classe controller
     */
}