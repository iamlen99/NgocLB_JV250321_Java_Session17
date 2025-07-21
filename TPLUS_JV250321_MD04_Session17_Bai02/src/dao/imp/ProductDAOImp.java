package dao.imp;

import dao.ProductDAO;
import entity.Product;
import entity.StatisticProduct;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImp implements ProductDAO {
    @Override
    public List<Product> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_product()}");
            ResultSet rs = callSt.executeQuery();
            listProducts = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getFloat("product_price"));
                product.setTitle(rs.getString("product_title"));
                product.setCreated(LocalDate.parse(rs.getString("product_created"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                product.setCatalog(rs.getString("product_catalog"));
                product.setStatus(rs.getBoolean("product_status"));
                listProducts.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listProducts;
    }

    @Override
    public boolean isNameExist(String productName) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call is_exist_product_name(?,?)}");
            callSt.setString(1, productName);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            int cntProduct = callSt.getInt(2);
            if (cntProduct > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean save(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_product(?,?,?,?,?,?)}");
            callSt.setString(1, product.getProductName());
            callSt.setFloat(2, product.getPrice());
            callSt.setString(3, product.getTitle());
            callSt.setDate(4, java.sql.Date.valueOf(product.getCreated()));
            callSt.setString(5, product.getCatalog());
            callSt.setBoolean(6, product.isStatus());
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public Product findById(int productId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Product product = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_product_by_id(?)}");
            callSt.setInt(1, productId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getFloat("product_price"));
                product.setTitle(rs.getString("product_title"));
                product.setCreated(LocalDate.parse(rs.getString("product_created"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                product.setCatalog(rs.getString("product_catalog"));
                product.setStatus(rs.getBoolean("product_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return product;
    }

    @Override
    public boolean update(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_product(?,?,?,?,?,?,?)}");
            callSt.setInt(1, product.getProductId());
            callSt.setString(2, product.getProductName());
            callSt.setFloat(3, product.getPrice());
            callSt.setString(4, product.getTitle());
            callSt.setDate(5, java.sql.Date.valueOf(product.getCreated()));
            callSt.setString(6, product.getCatalog());
            callSt.setBoolean(7, product.isStatus());
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean delete(int productId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_product(?)}");
            callSt.setInt(1, productId);
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<Product> searchByName(String productName) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_product_by_name(?)}");
            callSt.setString(1, productName);
            ResultSet rs = callSt.executeQuery();
            listProducts = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getFloat("product_price"));
                product.setTitle(rs.getString("product_title"));
                product.setCreated(LocalDate.parse(rs.getString("product_created"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                product.setCatalog(rs.getString("product_catalog"));
                product.setStatus(rs.getBoolean("product_status"));
                listProducts.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listProducts;
    }

    @Override
    public List<StatisticProduct> listStatisticProducts() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<StatisticProduct> listStatiticProduct = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call statistic_product_by_catalog()}");
            ResultSet rs = callSt.executeQuery();
            listStatiticProduct = new ArrayList<>();
            while (rs.next()) {
                StatisticProduct product = new StatisticProduct();
                product.setCatalog(rs.getString("product_catalog"));
                product.setCountProduct(rs.getInt("count_product"));
                listStatiticProduct.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listStatiticProduct;
    }
}
