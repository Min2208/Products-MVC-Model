package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "edit":
                editProduct(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            case "create":
                createProduct(req, resp);
                break;
            case "search":
                searchProduct(req,resp);
                break;
            default:
                break;

        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                showDeleteForm(req, resp);
                break;
            case "create":
                showCreateForm(req, resp);
                break;
            case "view":
                showProduct(req, resp);
            default:
                listProduct(req, resp);
                break;

        }
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = productService.fillAll();
        req.setAttribute("products", products);

        RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("hello");
        String name = req.getParameter("search");
        List<Product> newProduct = productService.fillByName(name);
        System.out.println(newProduct.get(0).getName()+" "+newProduct.get(0).getPrice());
        req.setAttribute("products", newProduct);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/search.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.fillById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("products", product);
            dispatcher = req.getRequestDispatcher("product/view.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = this.productService.fillById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("product/delete.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = this.productService.fillById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("product/edit.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String model = req.getParameter("model");
        String price = req.getParameter("price");
        int id = (int) (Math.random() * 10000);

        Product product = new Product(id, name, model, price);
        this.productService.save(product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        req.setAttribute("message", "New product was created");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = this.productService.fillById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            this.productService.delete(id);
            try {
                resp.sendRedirect("/products");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String model = req.getParameter("model");
        String price = req.getParameter("price");
        Product product = this.productService.fillById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            product.setName(name);
            product.setModel(model);
            product.setPrice(price);
            this.productService.update(id, product);
            req.setAttribute("product", product);
            req.setAttribute("message", "Product information was updated");
            dispatcher = req.getRequestDispatcher("product/edit.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
