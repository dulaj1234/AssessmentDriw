import { useState, useEffect } from 'react'
import { GetList, AddProduct } from "./services/services"
import Cart from "./pages/Cart"
import List from "./pages/List"

function App() {

  const [listdata, setListData] = useState([]);

  const [cartData, setcartData] = useState({ items: [], total: 0 })

  useEffect(() => {

    const getListData = async () => {
      const data = await GetList()
      setListData(data);
    }

    getListData();
  }, [])


  const addToCart = async (item) => {
    let { data } = await AddProduct(item);

    setcartData({
      items: item,
      total: data
    });

  }

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-3"></div>
        <div className="col-md-6"><List data={listdata} addToCart={addToCart} /></div>
        <div className="col-md-3"></div>
      </div>
      <div className="row">
        <div className="col-md-4"></div>
        <div className="col-md-4"> <br /></div>
        <div className="col-md-4"></div>
      </div>
      <div className="row">
        <div className="col-md-4"></div>
        <div className="col-md-4"> <Cart cartData={cartData} /></div>
        <div className="col-md-4"></div>
      </div>
    </div>
  );
}

export default App;
