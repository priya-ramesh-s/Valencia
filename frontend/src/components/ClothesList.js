import Clothes from "./Clothes"

const ClothesList = (props) => {

    const clothesComponents = props.clothes.map((clothes, index) => {
        return <Clothes clothes={clothes} key={index}/>
    });


    return (
        <>
            {clothesComponents}
        </>
    )

} 

export default ClothesList