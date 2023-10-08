import React from 'react'
import { SearchContainerProvider } from './components/search/search-container-provider'
import { CreationContainerProvider } from './components/creation/creation-container-provider'

import './app.css'

export const App: React.FC = () => {
    return (
        <div className='app' role='main'>
            <div>
                <SearchContainerProvider />
            </div>
            <div>
                <CreationContainerProvider />
            </div>
        </div>
    )
}