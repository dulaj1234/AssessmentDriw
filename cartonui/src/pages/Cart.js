

function Cart({ cartData }) {

    const Row = (data) => {
        return data && data.length > 0 && data.map((item) => {
            return <tr key={item.itemId}>
                <td>{item.itemId}</td>
                <td>{item.units}</td>
            </tr>
        })
    }

    return (
        <div>
            <table className="table">
                <thead>
                    <tr>
                        <td scope="col">ID</td>
                        <td scope="col">Quantity</td>
                    </tr>
                </thead>
                <tbody>
                    {Row(cartData.items)}
                </tbody>
            </table>
            <div style={{ float: 'right' }}><h6>Total: {cartData.total}</h6></div>
        </div>
    )
}

export default Cart
