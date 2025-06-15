
import { FaSearch } from "react-icons/fa";
type SearchBarProps = {
    label: string;
  };

export default function SearchBar({ label }: SearchBarProps)
{
    return (
        <div className="mt-12 flex justify-center">
            <div className="w-1/2 relative max-w-xl"> 
            <input
            type="text"
            placeholder={label}
            className=" p-2 border border-gray-300 rounded w-full searchin"/>
         <button className="searchb">
        <FaSearch />
        </button>  
        </div>   
        </div>
    );

}


