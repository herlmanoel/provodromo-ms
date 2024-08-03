// UnstyledTabsIntroduction.jsx
import React, { useState } from 'react';

const Tab = ({ children, isActive, onClick }) => (
    <button
        className={`w-full py-2.5 text-sm leading-5 font-medium text-gray-300 rounded-lg text-center ${
            isActive ? 'bg-white text-gray-900' : 'text-gray-400 hover:bg-white/[0.12]'
        }`}
        onClick={onClick}
    >
        {children}
    </button>
);

const TabPanel = ({ children, isActive }) => (
    <div className={`${isActive ? 'block' : 'hidden'} p-4 rounded-xl border border-gray-200 bg-white`}>
        {children}
    </div>
);

export default function Tabs({ tabs }) {
    const [activeTab, setActiveTab] = useState(0);

    return (
        <div className="p-4">
            <div className="flex space-x-1 bg-gray-800 p-1 rounded-xl">
                {tabs.map((tab, i) => (
                    <Tab key={i} isActive={activeTab === i} onClick={() => setActiveTab(i)}>
                        {tab.titulo}
                    </Tab>
                ))}
            </div>
            <div className="mt-2">
                {tabs.map((tab, i) => (
                    <TabPanel key={i} isActive={activeTab === i}>
                        {tab.corpo}
                    </TabPanel>
                ))}
            </div>
        </div>
    );
}
