import ProductCard from 'components/ProductCard';
import { Product } from 'types/product';

const Catalog = () => {
  const product : Product = {
    id: 1,
    name: "The Lord of the Rings",
    description: "The Lord of the Rings",
    price: 90.5,
    imgUrl: "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg",
    date: "2020-07-13T20:50:07.123450Z",
    categories: [
        {
            id: 2,
            name: "Eletrônicos"
        }
    ]
}
  
  return (  
    <div className="container my-4">
      <div className="row">
        <div className="col-sm-6 col-lg-4 col-xl-3">
          <ProductCard product={product}/>
        </div>

        <div className="col-sm-6 col-lg-4 col-xl-3">
          <ProductCard product={product}/>
        </div>

        <div className="col-sm-6 col-lg-4 col-xl-3">
          <ProductCard product={product}/>
        </div>

        <div className="col-sm-6 col-lg-4 col-xl-3">
          <ProductCard product={product}/>
        </div>

        <div className="col-sm-6 col-lg-4 col-xl-3">
          <ProductCard product={product}/>
        </div>
        
      </div>
    </div>
  );
};

export default Catalog;
