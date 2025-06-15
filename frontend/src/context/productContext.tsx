"use client";

import { CartInfo, Product } from '@/types/types';
import { createContext, useContext, useState, useEffect } from 'react';
import { ReactNode } from 'react';


const ProductContext = createContext<CartInfo | undefined>(undefined);

export const ProductProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [mounted, setMounted] = useState(false); // To track client mount
    const [productList, setProductList] = useState<Product[]>([]);
    const [total, setTotal] = useState<number>(0);

    useEffect(() => {
        setMounted(true); // Mark the component as mounted
    }, []);

    useEffect(() => {
        if (mounted && typeof window !== 'undefined') {
            const storedProducts = localStorage.getItem('productList');
            if (storedProducts) {
                const parsedProducts = JSON.parse(storedProducts);
                setProductList(parsedProducts);
                calculTotal(parsedProducts);
            }
        }
    }, [mounted]);

    useEffect(() => {
        if (mounted) {
            localStorage.setItem('productList', JSON.stringify(productList));
            calculTotal(productList);
        }
    }, [productList, mounted]);

    const calculTotal = (products: Product[]) => {
        const total = products.reduce((acc, product) => acc + product.prix * product.quantite, 0);
        setTotal(total);
        return total;
    };

    const addProduct = (product: Product) => {
        setProductList((prevProductList) => {
            const existingProduct = prevProductList.find((p) => p.id === product.id);
            let updatedProductList: Product[];

            if (existingProduct) {
                updatedProductList = prevProductList.map((p) =>
                    p.id === product.id ? { ...p, quantity: product.quantite } : p
                );
            } else {
                updatedProductList = [...prevProductList, { ...product, quantite: product.quantite }];
                
            }

            return updatedProductList;
        });
    };

    const updateQuantity = (product: Product, quantity: number) => {
        setProductList((prevProductList) =>
            prevProductList.map((p) => (p.id === product.id ? { ...p, quantity } : p))
        );
    };

    const removeProduct = (productId: number) => {
        setProductList((prevProductList) => prevProductList.filter((product) => product.id !== productId));
    };

    return (
        <ProductContext.Provider value={{ productList, total, calculTotal, addProduct, updateQuantity, removeProduct,numberProducts:productList.length }}>
            {children}
        </ProductContext.Provider>
    );
};

export const useProductContext = (): CartInfo => {
    const context = useContext(ProductContext);
    if (!context) {
        throw new Error('useProductContext must be used within a ProductProvider');
    }
    return context;
};
