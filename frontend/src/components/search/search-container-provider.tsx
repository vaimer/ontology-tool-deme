import { createContext, useContext, useMemo, useState } from 'react'
import { SearchInput } from './search-input'
import { SearchResult } from './search-result'
import { ClipLoader } from 'react-spinners'
import { Ontology } from '../types/ontology'
import { isEmpty } from 'lodash'

interface SearchContextType {
    loading: boolean;
    setLoading: (value: boolean) => void;
    searchResult: Ontology | undefined;
    setSearchResult: (value: Ontology | undefined) => void;
    loadingError: string | undefined;
    setLoadingError: (value: string | undefined) => void
}

const SearchContext = createContext<SearchContextType | undefined>(undefined)

export const useSearchContext = (): SearchContextType => {
    const context = useContext(SearchContext)

    if (context === undefined) {
        console.log('Search context must be initialized')

        return {} as SearchContextType;
    }

    return context
}
export const SearchContainerProvider = () => {
    const [loading, setLoading] = useState<boolean>(false)
    const [searchResult, setSearchResult] = useState<Ontology | undefined>(undefined)
    const [loadingError, setLoadingError] = useState<string | undefined>(undefined)

    const memorizedContextValues: SearchContextType = useMemo(() => {
        return {
            loading,
            searchResult,
            loadingError,
            setLoading,
            setSearchResult,
            setLoadingError,
        }
    }, [
        loading,
        searchResult,
        loadingError,
        setLoading,
        setSearchResult,
        setLoadingError,
    ])

    return (
        <SearchContext.Provider value={memorizedContextValues}>
            <SearchInput />
            <div className={"flex-container"}>
                {(!loading && !isEmpty(searchResult) && isEmpty(loadingError)) && <SearchResult />}
                {(!loading && !isEmpty(loadingError)) &&
                    <div>
                        {loadingError}
                    </div>
                }
                {loading &&  <ClipLoader size={65} />}
            </div>
        </SearchContext.Provider>
    )
}