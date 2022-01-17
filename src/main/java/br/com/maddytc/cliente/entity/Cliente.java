package br.com.maddytc.cliente.entity;
/**
 * entity é onde vamos guardar nossas entidades do banco de dados.
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

//@Data para criar os métodos getter e setter e o To String automaticamente.
@Data
//@AllArgsConstructor Para criar o construtor com as propriedades que criarmos de cliente.
@AllArgsConstructor
//@NoArgsConstructor Para criar um construtor vazio ou seja sem argumentos.
@NoArgsConstructor
//@Builder Para nos ajudar na criação de objetos Cliente.
@Builder
//@Entity Para informa que é uma entidade de banco de dados.
@Entity

public class Cliente implements Serializable {

    //Atributos Cliente
    //Como é uma entidade temos que definir qual dos atributos será o id então usamos a anotação @Id
    //Para que não seja obrigatório ficarmos, gerando Id a cada novo Cliente vamos fazer a seguinte anotação @GeneratedValue com a estratégia (strategy = GenerationType.AUTO).
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //No atributo nome vamos adicionar o nome da coluna do banco com a marcação @Column e colocar nullable = false para que o campo nao seja vazio tem que estar preenchido
    @Column(name =  "nome", nullable = false)
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;
}
