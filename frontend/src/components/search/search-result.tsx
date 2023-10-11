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
            <div className={"flex-container"}>
                <div>
                    <h4>Definition Properties</h4>
                    {searchResult?.definitionProperties &&
                        <p>
                            {
                                searchResult?.definitionProperties.map((value) => {
                                    return (
                                        <>
                                            <a href={value} target='_blank'>{value}</a> <br/>
                                        </>
                                    )
                                })
                            }
                        </p>
                    }
                </div>
                <div>
                    <h4>Synonym Properties</h4>
                    {searchResult?.synonymProperties &&
                        <p>
                            {
                                searchResult?.synonymProperties.map((value) => {
                                    return (
                                        <>
                                            <a href={value} target='_blank'>{value}</a> <br/>
                                        </>
                                    )
                                })
                            }
                        </p>
                    }
                </div>
            </div>
        </div>
    )
}