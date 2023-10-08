import { useCreationContext } from './creation-container-provider'

export const CreationResult: React.FC = () => {
    const {
        creationResult
    } = useCreationContext()

    return (
        <div>
            {creationResult && <p>{creationResult}</p> }
        </div>
    );
}