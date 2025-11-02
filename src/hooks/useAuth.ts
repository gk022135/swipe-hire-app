import { useNavigate } from "react-router-dom";
import { toast } from "sonner";

// Define a clear shape for your user data
export interface AuthUser {
  id: string;
  name: string;
  email: string;
  role?: string;
  avatar?: string;
  token?: string;
}

// Define what data the handleLoginSuccess expects
interface UseAuthReturn {
  handleLoginSuccess: (token: string, user: AuthUser) => void;
  logout: () => void;
  getCurrentUser: () => AuthUser | null;
  isAuthenticated: () => boolean;
}

export const useAuth = (): UseAuthReturn => {
  const navigate = useNavigate();

  const handleLoginSuccess = (token: string, user: AuthUser) => {
    try {
      const authData = { token, user };
      localStorage.setItem("auth_data", JSON.stringify(authData));

      toast.success(`Welcome back, ${user.name || "User"}!`);
      navigate("/feed");
    } catch (error) {
      console.error("Error storing auth data:", error);
      toast.error("Failed to save login session.");
    }
  };

  const logout = () => {
    localStorage.removeItem("auth_data");
    toast.info("You have been logged out.");
    navigate("/login");
  };

  const getCurrentUser = (): AuthUser | null => {
    const stored = localStorage.getItem("auth_data");
    if (!stored) return null;
    try {
      const parsed = JSON.parse(stored);
      return parsed.user as AuthUser;
    } catch {
      return null;
    }
  };

  const isAuthenticated = (): boolean => {
    const stored = localStorage.getItem("auth_data");
    if (!stored) return false;
    try {
      const parsed = JSON.parse(stored);
      return Boolean(parsed.token);
    } catch {
      return false;
    }
  };

  return { handleLoginSuccess, logout, getCurrentUser, isAuthenticated };
};
