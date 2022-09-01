import ProductCrudCard from "pages/Admin/Products/ProductCrudCard";
import { Link } from "react-router-dom";

import './styles.css';

const List = () => {

    const product = {
        "id": 1,
        "name": "The Lord of the Rings",
        "description": "The Lord of the Rings",
        "price": 90.5,
        "imgUrl": "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg",
        "date": "2020-07-13T20:50:07.123450Z",
        "categories": [
            {
                "id": 2,
                "name": "Eletrônicos"
            }
        ]
    }
    return (
        <div className="product-crud-container">
        <div className="product-crud-bar-container">
            <Link to="/admin/products/create">
                <button className="btn btn-primary text-white btn-crud-add">ADICIONAR</button>
            </Link>
            <div className="base-card product-filter-container">Serach bar</div>

        </div>
            <div className="row">
                <div className="col-sm-6 col-md-12">
                    <ProductCrudCard product={product}/>
                </div>
                <div className="col-sm-6 col-md-12">
                    <ProductCrudCard product={product}/>
                </div>
                <div className="col-sm-6 col-md-12">
                    <ProductCrudCard product={product}/>
                </div>
            </div>
        </div>
    )
}    
export default List;