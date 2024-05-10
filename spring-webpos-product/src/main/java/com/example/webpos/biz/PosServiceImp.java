package com.example.webpos.biz;

import com.example.webpos.db.PosDB;
import com.example.webpos.model.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;





@Service
@Component
public class PosServiceImp implements PosService {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }
    
    @Override
    public List<Product> products(){
        return posDB.getProducts();
    }

    //获得某个产品的信息
    public Product getProductById(String id){
        return posDB.getProduct(id);
    } 

    public Boolean setStockOfProduct(int num,String productId){
        Product item=posDB.getProduct(productId);
        if(item!=null){
            item.setQuantity(num);
            this.posDB.setProduct(item);
            return true;
        }
        else{
            return false;
        }
    }


    //在lab6中使用
    public Boolean changeStockOfProduct(int changenumber,String productId){
        Product item=posDB.getProduct(productId);
        if(item!=null){
            //修改数量
            item.changeQuantity(changenumber);

            //修改商品数据库
            this.posDB.setProduct(item);
            return true;
        }
        else{
            return false;
        }
    }
}