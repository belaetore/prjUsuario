package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;

public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping
	public Usuario criarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.salvaUsuario(usuario);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id) {
		Usuario usuario = usuarioService.buscaUsuarioId(id);
		if(usuario != null) {
			return ResponseEntity.ok(usuario);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Usuario>> buscaTodosUsuariosControl(){
		List<Usuario> Usuarios = usuarioService.buscaTodosUsuarios();
		return ResponseEntity.ok(Usuarios);
	}
	
		@DeleteMapping("/{id}")
		public void deletarUsuario(@PathVariable Long id) {
			usuarioService.excluirUsuario(id);
		}

		@PutMapping
		public ResponseEntity<Usuario> UsuarioAtualizado(@PathVariable Long id, @RequestBody Usuario usuario) {
			Usuario usuarioAtualizado = usuarioService.alterarUsuario(id, usuario);
			if (usuarioAtualizado != null) {
				return ResponseEntity.ok(usuarioAtualizado);
			} else {
				return null;
			}
		}
	}