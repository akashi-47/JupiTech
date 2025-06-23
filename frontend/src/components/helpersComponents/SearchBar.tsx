import { FaSearch } from "react-icons/fa";
type SearchBarProps = {
    label: string;
    value: string;
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    onSearch: () => void;
};

export default function SearchBar({ label, value, onChange, onSearch }: SearchBarProps)
{
    return (
        <div className="mt-12 flex justify-center">
            <div className="w-1/2 relative max-w-xl"> 
                <input
                    type="text"
                    placeholder={label}
                    value={value}
                    onChange={onChange}
                    className="p-2 border border-gray-300 rounded w-full searchin"
                    onKeyDown={e => { if (e.key === "Enter") onSearch(); }}
                />
                <button className="searchb" onClick={onSearch}>
                    <FaSearch />
                </button>  
            </div>   
        </div>
    );

}


