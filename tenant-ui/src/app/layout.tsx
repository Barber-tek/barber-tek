import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";
import Header from "@/components/header";
import Footer from "@/components/footer";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "Barber App",
  description: "A modern barber management system",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased bg-linear-to-b from-[#EEEFF6] to-white bg-fixed`}
      >
        <Header />
        <div className="relative flex flex-col">
          <div className="h-full w-full bg-[url(/background.png)] bg-repeat bg-center bg-cover flex-1 opacity-20 absolute -z-10" />
          <main className="flex flex-col items-center justify-center w-full gap-3">
            {children}
          </main>
        </div>
        <Footer />
      </body>
    </html>
  );
}
