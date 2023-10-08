import { useSearchContext } from './search-container-provider'

import './search-result.css'

export const SearchResult: React.FC = () => {
    const {
        searchResult,
    } = useSearchContext()

    return (
        <div className={'card'}>
            <h3>{searchResult?.id}</h3>
            <h2>{searchResult?.title}</h2>
            <p>{searchResult?.description}</p>
            <p>
                {
                    searchResult?.definitionProperties.map((value) => {
                        return (
                            <span>{value}&nbsp;</span>
                        )
                    })
                }
            </p>
            <p>
                {
                    searchResult?.synonymProperties.map((value) => {
                        return (
                            <span>{value}&nbsp;</span>
                        )
                    })
                }
            </p>
        </div>
    );
}