import { createContext, useContext, useMemo, useState } from 'react'
import { CreationResult } from './creation-result'
import { CreationForm } from './creation-form'
import { ClipLoader } from 'react-spinners'

interface CreationContextType {
    loading: boolean;
    setLoading: (value: boolean) => void;
    creationResult: string | undefined;
    setCreationResult: (value: string | undefined) => void
}

const CreationContext = createContext<CreationContextType | undefined>(undefined)

export const useCreationContext = (): CreationContextType => {
    const context = useContext(CreationContext)

    if (context === undefined) {
        console.log('Creation context must be initialized')
        return {} as CreationContextType
    }

    return context
}

export const CreationContainerProvider = () => {
    const [loading, setLoading] = useState<boolean>(false)
    const [creationResult, setCreationResult] = useState<string | undefined>(undefined)

    const memorizedContextValues: CreationContextType = useMemo(() => {
        return {
            loading,
            creationResult,
            setLoading,
            setCreationResult,
        }
    }, [
        loading,
        creationResult,
        setLoading,
        setCreationResult,
    ])

    return (
        <CreationContext.Provider value={memorizedContextValues}>
            <CreationForm />
            {loading && <ClipLoader size={65} />}
            {!loading && <CreationResult />}
        </CreationContext.Provider>
    )
}