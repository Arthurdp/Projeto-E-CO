package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eco.Comissao;

class TesteComissao {
	
		Comissao comissao1;
		Comissao comissao2;
		Comissao comissao3;
		Comissao comissao4;
		
	@Test
	void testComissao() {
		assertThrows(IllegalArgumentException.class, ()-> comissao1 = new Comissao("", "111111111-1,111111111-2,111111111-3"),"Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> comissao2 = new Comissao(null, "111111111-1,111111111-2,111111111-3"),"Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		assertThrows(IllegalArgumentException.class, ()-> comissao3 = new Comissao("uzladrao", ""),"Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
		assertThrows(NullPointerException.class, ()-> comissao4 = new Comissao("uzladrao", null),"Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
	}

}
