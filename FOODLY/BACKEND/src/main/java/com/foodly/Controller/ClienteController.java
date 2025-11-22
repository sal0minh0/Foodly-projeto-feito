package com.foodly.Controller;

import com.foodly.DAO.ClienteDAO;
import com.foodly.DAO.UsuarioDAO;
import com.foodly.Models.Cliente;
import com.foodly.Models.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final UsuarioDAO usuarioDAO;
    private final ClienteDAO clienteDAO;

    public ClienteController() {
        this.usuarioDAO = new UsuarioDAO();
        this.clienteDAO = new ClienteDAO();
    }

    /**
     * POST /api/clientes/cadastrar
     * DEVE vir ANTES de /{id}
     */
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCliente(@RequestBody ClienteRequestDTO request) {
        try {
            System.out.println("=== Cadastro Cliente ===");
            System.out.println("Nome: " + request.getNome());
            
            if (request.getNome() == null || request.getNome().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Nome é obrigatório"));
            }
            
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Email é obrigatório"));
            }
            
            if (request.getSenha() == null || request.getSenha().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Senha é obrigatória"));
            }
            
            if (!request.getSenha().equals(request.getConfirmarSenha())) {
                return ResponseEntity.badRequest().body(new ErrorResponse("As senhas não coincidem"));
            }

            Usuario usuario = new Usuario();
            usuario.setNome(request.getNome());
            usuario.setEmail(request.getEmail());
            usuario.setSenhaHash(request.getSenha()); 
            usuario.setTelefone(request.getTelefone());
            usuario.setTipoUsuario("cliente");
            usuario.setCriadoEm(LocalDateTime.now());

            int usuarioId = usuarioDAO.salvar(usuario);

            Cliente cliente = new Cliente();
            cliente.setUsuarioId(usuarioId);
            cliente.setEnderecoPadrao(request.getEnderecoPadrao() != null ? request.getEnderecoPadrao() : "");

            int clienteId = clienteDAO.salvar(cliente);
            cliente.setId(clienteId);

            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ClienteResponseDTO(clienteId, usuarioId, request.getNome(), request.getEmail()));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Erro: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> listarClientes() {
        try {
            List<Cliente> clientes = clienteDAO.listarTodos();
            return ResponseEntity.ok(clientes);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Erro ao listar clientes: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable int id) {
        try {
            Cliente cliente = clienteDAO.buscarPorId(id);
            if (cliente == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(cliente);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Erro ao buscar cliente: " + e.getMessage()));
        }
    }

    // DTOs
    static class ClienteRequestDTO {
        private String nome;
        private String email;
        private String senha;
        private String confirmarSenha;
        private String telefone;
        private String enderecoPadrao;

        public ClienteRequestDTO() {}

        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
        public String getConfirmarSenha() { return confirmarSenha; }
        public void setConfirmarSenha(String confirmarSenha) { this.confirmarSenha = confirmarSenha; }
        public String getTelefone() { return telefone; }
        public void setTelefone(String telefone) { this.telefone = telefone; }
        public String getEnderecoPadrao() { return enderecoPadrao; }
        public void setEnderecoPadrao(String enderecoPadrao) { this.enderecoPadrao = enderecoPadrao; }
    }

    static class ClienteResponseDTO {
        private int clienteId;
        private int usuarioId;
        private String nome;
        private String email;

        public ClienteResponseDTO(int clienteId, int usuarioId, String nome, String email) {
            this.clienteId = clienteId;
            this.usuarioId = usuarioId;
            this.nome = nome;
            this.email = email;
        }

        public int getClienteId() { return clienteId; }
        public int getUsuarioId() { return usuarioId; }
        public String getNome() { return nome; }
        public String getEmail() { return email; }
    }

    static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() { return message; }
    }
}
