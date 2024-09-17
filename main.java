public class Produto {
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void vender(int quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
        } else {
            System.out.println("Quantidade insuficiente para venda.");
        }
    }

    public void repor(int quantidade) {
        this.quantidade += quantidade;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + preco + " (Quantidade: " + quantidade + ")";
    }
}

public class Cliente {
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + " (CPF: " + cpf + ")";
    }
}

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private Cliente cliente;
    private List<Produto> produtos;
    private double total;

    public Venda(Cliente cliente) {
        this.cliente = cliente;
        produtos = new ArrayList<>();
        total = 0.0;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        produto.vender(quantidade);
        produtos.add(produto);
        total += produto.getPreco() * quantidade;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Venda:\n");
        sb.append(cliente).append("\n");
        for (Produto produto : produtos) {
            sb.append(produto.getNome()).append("\n");
        }
        sb.append("Total: R$ ").append(total);
        return sb.toString();
    }
}

import java.util.HashMap;
import java.util.Map;

public class Banca {
    private Map<String, Produto> inventario;
    private Map<String, Cliente> clientes;

    public Banca() {
        inventario = new HashMap<>();
        clientes = new HashMap<>();
    }

    public void adicionarProduto(Produto produto) {
        inventario.put(produto.getNome(), produto);
    }

    public void removerProduto(String nome) {
        inventario.remove(nome);
    }

    public Produto consultarProduto(String nome) {
        return inventario.get(nome);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.put(cliente.getCpf(), cliente);
    }

    public void removerCliente(String cpf) {
        clientes.remove(cpf);
    }

    public Cliente consultarCliente(String cpf) {
        return clientes.get(cpf);
    }

    public void realizarVenda(String cpfCliente, String nomeProduto, int quantidade) {
        Cliente cliente = clientes.get(cpfCliente);
        Produto produto = inventario.get(nomeProduto);

        if (cliente != null && produto != null) {
            Venda venda = new Venda(cliente);
            venda.adicionarProduto(produto, quantidade);
            System.out.println(venda);
        } else {
            System.out.println("Cliente ou produto não encontrado.");
        }
    }

    public void listarInventario() {
        for (Produto produto : inventario.values()) {
            System.out.println(produto);
        }
    }

    public void listarClientes() {
        for (Cliente cliente : clientes.values()) {
            System.out.println(cliente);
        }
    }

public class Main {
    public static void main(String[] args) {
        Banca banca = new Banca();
        
        // Adicionar produtos
        Produto jornal = new Produto("Jornal", 2.50, 100);
        Produto revista = new Produto("Revista", 5.00, 50);
        banca.adicionarProduto(jornal);
        banca.adicionarProduto(revista);

        // Adicionar clientes
        Cliente cliente1 = new Cliente("Maria Silva", "12345678900");
        Cliente cliente2 = new Cliente("João Pereira", "09876543211");
        banca.adicionarCliente(cliente1);
        banca.adicionarCliente(cliente2);

        // Listar inventário e clientes
        banca.listarInventario();
        banca.listarClientes();
        
        // Realizar uma venda
        banca.realizarVenda("12345678900", "Jornal", 3);
        banca.listarInventario(); // Verificar o inventário após a venda
    }
}