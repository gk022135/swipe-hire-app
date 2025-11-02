import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Tabs, TabsList, TabsTrigger, TabsContent } from "@/components/ui/tabs";
import logo from "@/assets/logo.png";
import { Job } from "@/types/jobs";
import { mockJobs } from "@/constant";
import JobDetailsModal from "@/components/JobDetailsModal"; // ✅ Import modal

const Applications = () => {
  const [activeTab, setActiveTab] = useState("applied");
  const [selectedJob, setSelectedJob] = useState<Job | null>(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  // Mock data (replace with your stored applied/saved/skipped data)
  const appliedJobs = mockJobs.slice(0, 2);
  const savedJobs = mockJobs.slice(2, 4);
  const skippedJobs: Job[] = [];

  // --- Open modal in readonly mode ---
  const openJobDetails = (job: Job) => {
    setSelectedJob(job);
    setIsModalOpen(true);
  };

  const renderJobCard = (job: Job) => (
    <div
      key={job.id}
      className="gradient-card rounded-xl p-4 sm:p-6 hover:shadow-md transition cursor-pointer"
      onClick={() => openJobDetails(job)} // ✅ opens modal instead of navigate
    >
      <div className="flex items-start gap-3 sm:gap-4">
        <div className="w-14 h-14 sm:w-16 sm:h-16 rounded-xl bg-secondary flex items-center justify-center text-xl sm:text-2xl font-bold text-primary flex-shrink-0 shadow-md">
          {job.companyLogo ? (
            <img
              src={job.companyLogo}
              alt={job.company}
              className="w-full h-full object-cover rounded-xl"
            />
          ) : (
            job.company.charAt(0)
          )}
        </div>

        <div className="flex-1 min-w-0">
          <h3 className="text-lg sm:text-xl font-semibold mb-1 truncate">
            {job.title}
          </h3>
          <p className="text-muted-foreground text-sm truncate flex items-center gap-1">
            {job.company}
          </p>
          <p className="text-xs sm:text-sm text-muted-foreground mt-1">
            {job.location} • {job.jobType}
          </p>
        </div>

        <Badge className="bg-primary/10 text-primary border-primary/20 text-xs sm:text-sm">
          {job.rating.toFixed(1)}
        </Badge>
      </div>
    </div>
  );

  return (
    <div className="min-h-screen gradient-subtle pb-24">
      {/* Header */}
      <header className="sticky top-0 z-50 glass backdrop-blur-xl border-b">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 py-4 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <img src={logo} alt="SwipeHire" className="w-8 h-8 rounded-md" />
            <h1 className="text-lg sm:text-xl font-bold tracking-tight">
              My Jobs
            </h1>
          </div>
        </div>
      </header>

      {/* Tabs */}
      <main className="max-w-4xl mx-auto px-4 sm:px-6 py-6">
        <Tabs value={activeTab} onValueChange={setActiveTab}>
          <TabsList className="grid grid-cols-3 mb-6">
            <TabsTrigger value="applied">Applied</TabsTrigger>
            <TabsTrigger value="saved">Saved</TabsTrigger>
            <TabsTrigger value="skipped">Skipped</TabsTrigger>
          </TabsList>

          <TabsContent value="applied">
            {appliedJobs.length ? (
              <div className="space-y-3">{appliedJobs.map(renderJobCard)}</div>
            ) : (
              <p className="text-center text-muted-foreground py-10">
                No applied jobs yet
              </p>
            )}
          </TabsContent>

          <TabsContent value="saved">
            {savedJobs.length ? (
              <div className="space-y-3">{savedJobs.map(renderJobCard)}</div>
            ) : (
              <p className="text-center text-muted-foreground py-10">
                No saved jobs yet
              </p>
            )}
          </TabsContent>

          <TabsContent value="skipped">
            {skippedJobs.length ? (
              <div className="space-y-3">{skippedJobs.map(renderJobCard)}</div>
            ) : (
              <p className="text-center text-muted-foreground py-10">
                No skipped jobs
              </p>
            )}
          </TabsContent>
        </Tabs>
      </main>

      {/* ✅ Job Details Modal in readonly mode */}
      <JobDetailsModal
        job={selectedJob}
        open={isModalOpen}
        onOpenChange={setIsModalOpen}
        onApply={() => {}}
        onSave={() => {}}
        readonly // ✅ hides Apply/Save buttons
      />
    </div>
  );
};

export default Applications;
