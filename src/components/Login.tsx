import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Checkbox } from "@/components/ui/checkbox";
import { Eye, EyeOff, Mail, Lock } from "lucide-react";
import { toast } from "sonner";
import logo from "@/assets/logo.png";
import { GoogleLogin, CredentialResponse } from "@react-oauth/google";
import { jwtDecode } from "jwt-decode"; // ✅ correct named import
import { useAuth } from "@/hooks/useAuth"; // ✅ reuse your hook

interface GoogleUser {
  name?: string;
  email?: string;
  picture?: string;
  sub?: string;
}

const Login = () => {
  const navigate = useNavigate();
  const { handleLoginSuccess } = useAuth();

  const [showPassword, setShowPassword] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [rememberMe, setRememberMe] = useState(false);
  const [loading, setLoading] = useState(false);

  const handleLogin = (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    setTimeout(() => {
      if (email && password) {
        const hasOnboarded = localStorage.getItem("onboarding_completed");
        const demoUser = { id: "1", name: "Demo User", email };
        handleLoginSuccess("demo_token", demoUser);
        navigate(hasOnboarded ? "/feed" : "/onboarding");
      } else {
        toast.error("Invalid credentials");
      }
      setLoading(false);
    }, 1000);
  };

  const handleGoogleLogin = (response: CredentialResponse) => {
    try {
      if (!response.credential) throw new Error("Missing Google credential");
      const decoded = jwtDecode<GoogleUser>(response.credential);
      const user = {
        id: decoded.sub || "unknown",
        name: decoded.name || "User",
        email: decoded.email || "unknown",
        avatar: decoded.picture,
      };
      handleLoginSuccess(response.credential, user);
    } catch (err) {
      console.error("Google login error:", err);
      toast.error("Google login failed. Please try again.");
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center px-4 sm:px-5 py-8 gradient-subtle">
      <div className="w-full max-w-sm sm:max-w-md">
        {/* Header */}
        <div className="text-center mb-6 sm:mb-8 animate-fade-in">
          <img
            src={logo}
            alt="SwipeHire"
            className="w-14 h-14 sm:w-20 sm:h-20 mx-auto mb-4 drop-shadow-md"
          />
          <h1 className="text-2xl sm:text-3xl font-bold">Welcome back</h1>
          <p className="text-sm sm:text-base text-muted-foreground mt-1">
            Continue your job search journey
          </p>
        </div>

        {/* Form */}
        <div className="bg-card/60 backdrop-blur-md rounded-2xl p-5 sm:p-8 shadow-lg border border-border animate-fade-in-scale">
          <form onSubmit={handleLogin} className="space-y-5">
            <div>
              <Label htmlFor="email">Email</Label>
              <div className="relative mt-1">
                <Mail className="absolute left-3 top-3 h-5 w-5 text-muted-foreground" />
                <Input
                  id="email"
                  type="email"
                  placeholder="you@example.com"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="pl-10 text-sm"
                  required
                />
              </div>
            </div>

            <div>
              <Label htmlFor="password">Password</Label>
              <div className="relative mt-1">
                <Lock className="absolute left-3 top-3 h-5 w-5 text-muted-foreground" />
                <Input
                  id="password"
                  type={showPassword ? "text" : "password"}
                  placeholder="••••••••"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="pl-10 pr-10 text-sm"
                  required
                />
                <button
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-3 top-3 text-muted-foreground hover:text-foreground transition"
                >
                  {showPassword ? <EyeOff className="w-5 h-5" /> : <Eye className="w-5 h-5" />}
                </button>
              </div>
            </div>

            <div className="flex items-center justify-between text-xs sm:text-sm">
              <div className="flex items-center gap-2">
                <Checkbox
                  id="remember"
                  checked={rememberMe}
                  onCheckedChange={(c) => setRememberMe(c as boolean)}
                />
                <Label htmlFor="remember" className="cursor-pointer">
                  Remember me
                </Label>
              </div>
              <Button variant="link" className="h-auto p-0 text-xs sm:text-sm">
                Forgot password?
              </Button>
            </div>

            <Button type="submit" size="lg" className="w-full" disabled={loading}>
              {loading ? "Logging in..." : "Login"}
            </Button>
          </form>

          {/* Divider */}
          <div className="relative my-6">
            <div className="absolute inset-0 flex items-center">
              <div className="w-full border-t border-border" />
            </div>
            <div className="relative flex justify-center text-xs uppercase">
              <span className="bg-card px-2 text-muted-foreground">Or continue with</span>
            </div>
          </div>

          {/* Google Login */}
          <div className="flex justify-center mt-4">
            <GoogleLogin
              onSuccess={handleGoogleLogin}
              onError={() => toast.error("Google login failed")}
              shape="pill"
              width="280"
            />
          </div>

          <p className="text-center text-xs sm:text-sm text-muted-foreground mt-6">
            Don’t have an account?{" "}
            <Button variant="link" className="h-auto p-0" onClick={() => navigate("/signup")}>
              Sign up
            </Button>
          </p>
        </div>
      </div>
    </div>
  );
};

export default Login;
