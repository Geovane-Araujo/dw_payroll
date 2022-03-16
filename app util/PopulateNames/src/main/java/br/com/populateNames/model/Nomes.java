package br.com.populateNames.model;

import com.jatom.anotations.Id;
import com.jatom.anotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("person")
public class Nomes {

    @Id
    private int id;

    private String nome;
}
