
import { useState } from 'react'
import { Modal } from 'bootstrap'


function List({ data, addToCart }) {
    const [itemList, setItemList] = useState([])
    const [itemData, setItemData] = useState({ itemId: 0, units: 0 })

    const addItemToCart = () => {
        setItemList([...itemList, itemData])
        setItemData({ itemId: 0, units: 0 })
    }

    const checkout = () => {
        addToCart(itemList)
    }



    const Row = ({ data }, setItemData) => {
        return data && data.length > 0 && data.map((item) => {
            return <tr key={item.itemMaster}>
                <td>{item.itemMaster}</td>
                <td>{item.itemsPerCarton}</td>
                <td>{item.cartonPrice}</td>
                <td>
                    <button type="button"
                        className="btn btn-primary"
                        data-bs-toggle="modal"
                        data-bs-target="#exampleModal"
                        onClick={e => setItemData({ ...itemData, itemId: item.itemMaster })}>
                        Add
                    </button>
                </td>
            </tr>
        })
    }

    return (
        <div>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Items Per Carton</th>
                        <th scope="col">Carton Price</th>
                        <th scope="col">Option</th>
                    </tr>
                </thead>
                <tbody>
                    {Row(data, setItemData)}
                </tbody>
            </table>

            <button type="button"
                className="btn btn-primary"
                onClick={e => checkout()}>
                Checkout
            </button>

            <div className="modal fade" id="exampleModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="exampleModalLabel">Add Unit To Cart</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <input type="text" placeholder="Unit Quantity" value={itemData.units} onChange={e => setItemData({ ...itemData, units: parseInt(e.target.value) })}></input>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-primary" data-bs-dismiss="modal" onClick={e => addItemToCart()}>Add To Cart</button>
                        </div>
                    </div>
                </div>
            </div>
        </div >
    )
}

export default List
