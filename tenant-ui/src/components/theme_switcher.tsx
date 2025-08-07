'use client';
import { ComputerDesktopIcon, MoonIcon, SunIcon } from '@heroicons/react/24/outline';
import { useTheme } from 'next-themes';

export default function ThemeSwitcher() {
    const { theme, setTheme, systemTheme } = useTheme();

    const isDarkMode = theme === 'dark'
    const isLightMode = theme === 'light'
    const isSystemMode = theme === 'system'

    return (
        <div className='flex items-center rounded-full p-2 border border-gray-300'>
            <button onClick={() => setTheme('light')} className={'p-2 text-gray-300 hover:bg-gray-300 rounded-full hover:text-gray-900' + (isLightMode ? ' bg-gray-300 text-gray-900' : '')}>
                <SunIcon width={16} />
            </button>
            <button onClick={() => setTheme(systemTheme || 'light')} className={'p-2 text-gray-300 hover:bg-gray-300 rounded-full hover:text-gray-900' + (isSystemMode ? ' bg-gray-300 text-gray-900' : '')}>
                <ComputerDesktopIcon width={16} />
            </button>
            <button onClick={() => setTheme('dark')} className={'p-2 text-gray-300 hover:bg-gray-300 rounded-full hover:text-gray-900' + (isDarkMode ? ' bg-gray-300 text-gray-900' : '')}>
                <MoonIcon width={16} />
            </button>
        </div>
    );
}