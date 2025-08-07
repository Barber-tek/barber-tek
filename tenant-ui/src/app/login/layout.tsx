export default function LoginLayout({children}: {children: React.ReactNode}) {
    return (
        <div className="login-layout">
            <header>
                <h1>Login</h1>
            </header>
            <main>
                {children}
            </main>
        </div>
    );

}