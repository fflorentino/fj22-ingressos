package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class SessaoTest {
	
	private Filme rogueOne;
	private Sala sala3D;
	private Sessao sessao;
	private Lugar a1;
	private Lugar a2;
	private Lugar a3;
	
	@Before
	public void preparaSessoes() {
		
		this.rogueOne = new Filme("Rogue One", Duration.ofMinutes(120), "Ficção", new BigDecimal("12"));
		this.sala3D = new Sala("Sala 3D", new BigDecimal("20.5"));
		this.sessao = new Sessao(LocalTime.parse("10:00:00"), rogueOne, sala3D);
		this.a1 = new Lugar("A", 1);
		this.a2 = new Lugar("A", 2);
		this.a3 = new Lugar("A", 3);
	}
	
	@Test
	public void oPrecoDaSesaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFile() {

		
		BigDecimal somaDosPrecosDaSalaEFilme = sala3D.getPreco().add(rogueOne.getPreco());
		
		Assert.assertEquals(somaDosPrecosDaSalaEFilme, sessao.getPreco());
	}
	
	@Test
	public void garanteQueOLugarA1estaOcupadoEOsLugaresA2EA3Disponivel() {
		
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, a1);
		
		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());
		
		sessao.setIngressos(ingressos);
		
		Assert.assertFalse(sessao.isDisponivel(a1));
		Assert.assertTrue(sessao.isDisponivel(a2));
		Assert.assertTrue(sessao.isDisponivel(a3));
		
		
	}

}
