package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class DescontoTeste {
	
	private Filme rogueOne;
	private Sala sala3D;
	private Sessao sessao;
	
	@Before
	public void preparaSessoes() {
		
		this.rogueOne = new Filme("Rogue One", Duration.ofMinutes(120), "Ficção", new BigDecimal("12"));
		this.sala3D = new Sala("Sala 3D", new BigDecimal("20.5"));
		this.sessao = new Sessao(LocalTime.parse("10:00:00"), rogueOne, sala3D);
	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		
		Ingresso ingresso = new Ingresso(sessao, new SemDesconto());
		BigDecimal precoEsperado = new BigDecimal("32.50");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
		
	}
	
	@Test
	public void deveConcederDescontoParaIngressoDeClientesDeBanco() {
		
		Ingresso ingresso = new Ingresso(sessao, new DescontoParaBancos());
		BigDecimal precoEsperado = new BigDecimal("22.75");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
		
	}
	
	@Test
	public void deveConcederDescontoParaIngressoDeEstudando() {
		
		Ingresso ingresso = new Ingresso(sessao, new DescontoParaEstudantes());
		BigDecimal precoEsperado = new BigDecimal("16.25");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
		
	}
}
