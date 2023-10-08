import { useSearchContext } from './search-container-provider'
import { useState } from 'react'
import { Ontology } from '../types/ontology'

import './search-input.css'

export const SearchInput: React.FC = () => {
    const { setLoading, setSearchResult } = useSearchContext()

    const [searchingOntology, setSearchingOntology] = useState<string | null>(null)

    const searchOntology = () => {
        if (searchingOntology) {
            setLoading(true)
            fetch(`http://localhost:8080/ontology/${searchingOntology}`)
                .then(async (response) => {
                    setSearchResult((await response.json()) as Ontology)
                    setLoading(false)
                })
                .catch((error) => {
                    console.log(`Error while loading ontology data ${error}`)
                    setLoading(false)
                })
        } else {
            console.log('There is no id to search')
        }
    }

    return (
        <>
            <label>
                <input type={'text'} placeholder={'Type ontology id to search'} className={'searchInput'}
                       onChange={(value) => {
                           setSearchingOntology(value.currentTarget.value)
                       }} />
            </label>
            <button className={"button"} onClick={searchOntology}> Search</button>
        </>
    )
}