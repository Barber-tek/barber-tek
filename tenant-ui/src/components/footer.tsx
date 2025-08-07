import { FaGithub, FaLinkedin, FaTwitter } from "react-icons/fa";
import ThemeSwitcher from "@/components/theme_switcher";

export default function Footer() {
    return (
        <footer className="bg-white text-gray-500 border-t border-gray-200  p-4 w-full max-h-[100px] h-[100px] flex items-center justify-between">
            <div className="container mx-auto text-center">
                <p className="text-sm">
                    &copy; {new Date().getFullYear()} BeruTek. All rights reserved.
                </p>
                <div className="flex justify-center items-center gap-4 mt-2">
                    <a href="https://github.com"><FaGithub /></a>
                    <a href="https://linkedin.com"><FaLinkedin /></a>
                    <a href="https://x.com"><FaTwitter /></a>
                </div>
            </div>
            <ThemeSwitcher />
        </footer>
    );
}