import './creation-form.css'
import { useState } from 'react'
import { useCreationContext } from './creation-container-provider'

import { isEmpty } from 'lodash'

export const CreationForm: React.FC = () => {
    const { setCreationResult, setLoading } = useCreationContext()

    const [id, setId] = useState<string>('')
    const [title, setTitle] = useState<string>('')
    const [description, setDescription] = useState<string>('')
    const [definitionProperties, setDefinitionProperties] = useState<string[]>([])
    const [synonymProperties, setSynonymProperties] = useState<string[]>([])

    const resetFormState = () => {
        setId('')
        setTitle('')
        setDescription('')
        setDefinitionProperties([])
        setSynonymProperties([])
    }

    const saveOntology = () => {
        if (isEmpty(id) || isEmpty(title) || isEmpty(description) || isEmpty(definitionProperties) || isEmpty(synonymProperties)) {
            setCreationResult('Please fill all form fields')
            return
        }

        setLoading(true)
        setCreationResult(undefined)
        fetch(`http://localhost:8080/ontology/`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ id, title, description, definitionProperties, synonymProperties }),
        })
            .then(async (response) => {
                setCreationResult(`Ontology was successfully created ${Object.entries(await response.json()).map(([key, value]) => {
                    return (` ${key}: ${value}`);
                }).toString()}`)
                resetFormState()
                setLoading(false)
            })
            .catch((error) => {
                setCreationResult(`Error while creation new ontology ${error}`)
                setLoading(false)
            })
    }

    return (
        <div className={'container'}>
            <input type={'text'} placeholder={'Type ontology id'} className={'creationInput'} value={id}
                   onChange={(value) => {
                       setId(value.currentTarget.value)
                   }} />
            <input type={'text'} placeholder={'Type ontology title'} className={'creationInput'} value={title}
                   onChange={(value) => {
                       setTitle(value.currentTarget.value)
                   }} />
            <input type={'text'} placeholder={'Type ontology description'} className={'creationInput'}
                   value={description}
                   onChange={(value) => {
                       setDescription(value.currentTarget.value)
                   }} />
            <input type={'text'}
                   placeholder={'Type ontology definition properties, use spaces as delimiter for definitions'}
                   value={definitionProperties}
                   className={'creationInput'}
                   onChange={(value) => {
                       setDefinitionProperties(value.currentTarget.value.split(' '))
                   }} />
            <input type={'text'}
                   placeholder={'Type ontology synonym properties, use spaces as delimiter for definitions'}
                   value={synonymProperties}
                   className={'creationInput'}
                   onChange={(value) => {
                       setSynonymProperties(value.currentTarget.value.split(' '))
                   }} />
            <button className={'button'} onClick={saveOntology}> Create</button>
        </div>
    )
}