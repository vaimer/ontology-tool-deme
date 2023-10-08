import { createContext, useContext, useMemo, useState } from 'react'

interface CreationContextType {
    loading: boolean;
    setLoading: (value: boolean) => void;
    creationResult: string | undefined;
    setCreationResult: (value: string) => void
}

const CreationContext = createContext<CreationContextType | undefined>(undefined)

export const useCreationContext = (): CreationContextType => {
    const context = useContext(CreationContext)

    if (context === undefined) {
        throw new Error('Creation context must be initialized')
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
            <h1>Some Creation form</h1>
        </CreationContext.Provider>
    )
}