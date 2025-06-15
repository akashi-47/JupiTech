

export interface Product {
    id: number;
    title: string;
    prix: number ;
    description: string;
    imageURL: string;
    quantite: number;
    categorie: string;
  }

export interface CartInfo {
    productList: Product[]; 
    total:number;
    calculTotal: (products: Product[]) => number; 
    addProduct: (product: Product) => void;
    updateQuantity: (product: Product,quantity:number) => void;
    removeProduct: (productId: number) => void;
    numberProducts: number;
}
export interface UserContextType {
token : string;
role: string;
setToken: (token: string) => void;
isLoggedIn: boolean; 
setIsLoggedIn: (isLoggedIn: boolean) => void; // Function to set logged in state


}
