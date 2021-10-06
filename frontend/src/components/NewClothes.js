import React from 'react'
import useState from "react"; 

const NewClothes = (props) => {

    const [type, setType] = useState("");
    const [subtype, setSubType] = useState("");
    const [color, setColor] = useState("");
    const [size, setSize] = useState("");
    const [material, setMaterial] = useState("");
    const [price, setPrice] = useState(0);
    const [quantity, setQuantity] = useState(0);


    const onTypeChange = (evt => {
        setType(evt.target.value);
    })

    const onSubTypeChange = (evt => {
        setSubType(evt.target.value);
    })

    const onColor = (evt => {
        setColor(evt.target.value);
    })
    const onSizeChange = (evt => {
        setSize(evt.target.value);
    })

    const onMaterialChange = (evt => {
        setMaterial(evt.target.value);
    })

    const onPrice = (evt => {
        setPrice(evt.target.value);
    })

    const onQuantity = (evt => {
        setQuantity(evt.target.value);
    })

    const onClothesSubmission = ((evt) => {
        evt.preventDefault();
        const newClothes = {
            type: type,
            subtype: subtype,
            color: color,
            size: size,
            material: material,
            price: price,
            quantity: quantity

        }
        props.handleClothesSubmission(newClothes);
        setType("");
        setSubType("");
        setColor("");
        setSize("");
        setMaterial("");
        setPrice(0);
        setQuantity(0)
    })

    return (
        <form onSubmit={onClothesSubmission}>
            <label for="type">Type:</label>
            <input type="text" id="clothes" name="clothes" value={type} onChange={onTypeChange}/>

            <label for="subtype">SubType:</label>
            <input type="text" id="subtype" name="subtype" value={subtype} onChange={onSubTypeChange}/>

            <label for="color">Color:</label>
            <input type="text" id="color" name="color" value={color} onChange={onColorChange}/>

            <label for="size">Size:</label>
            <input type="text" id="size" name="size" value={size} onChange={onSizeChange}/>
            
            <label for="material">Material:</label>
            <input type="text" id="material" name="material" value={material} onChange={onMaterialChange}/>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" min="0" value={price} onChange={onPriceChange}/>

            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" min="0" value={quantity} onChange={onQuantityChange}/>

            <input type="submit" value="add new clothes"/>
        </form>
    )

}

export default NewClothes;