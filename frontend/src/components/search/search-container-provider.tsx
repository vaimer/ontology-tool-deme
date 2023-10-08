import { createContext, useContext, useMemo, useState } from 'react'
import { SearchInput } from './search-input'
import { SearchResult } from './search-result'
import { ClipLoader } from 'react-spinners'
import { Ontology } from '../types/ontology'

interface SearchContextType {
    loading: boolean;
    setLoading: (value: boolean) => void;
    searchResult: Ontology | undefined
    setSearchResult: (value: Ontology | undefined) => void
}

const SearchContext = createContext<SearchContextType | undefined>(undefined)

export const useSearchContext = (): SearchContextType => {
    const context = useContext(SearchContext)

    if (context === undefined) {
        throw new Error('Search context must be initialized')
    }

    return context
}
export const SearchContainerProvider = () => {
    const [loading, setLoading] = useState<boolean>(false)
    const [searchResult, setSearchResult] = useState<Ontology | undefined>(undefined)

    const memorizedContextValues: SearchContextType = useMemo(() => {
        return {
            loading,
            searchResult,
            setLoading,
            setSearchResult,
        }
    }, [
        loading,
        searchResult,
        setLoading,
        setSearchResult,
    ])

    return (
        <SearchContext.Provider value={memorizedContextValues}>
            <SearchInput />
            {(!loading && searchResult) && <SearchResult />}
            {loading &&  <ClipLoader color={'#fff'} size={150} />}
        </SearchContext.Provider>
    )
}