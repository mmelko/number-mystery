@Path("/stats/")
public class Statistics {
	private static long attempts = 0;
	private static long successes = 0;

	@GET
	@Path("/attempts")
	@Produces("application/json")
	public String getActivities() {
		return "Total Number of attempts:" + attempts;
	}

	@POST
	@Path("/attempts")
	@Produces("application/json")
	public String increaseActivitiesByOne() {

		StatsService.attempts++;
		return "Number of attempts:" + attempts;
	}

	@GET
	@Path("/successful")
	@Produces("application/json")
	public String getLogMessages() {
		return "Number successful guesses:" + successes;
	}

	@POST
	@Path("/successful")
	@Produces("application/json")
	public String increaseAuditMessagesByOne() {
		StatsService.successes++;
		return "Number successful guesses:" + successes;
	}

	@GET
	@Path("/")
	@Produces("application/json")
	public String getStats() {
		return "attempts:" + attempts + "\nSuccess: " + successes + "\n Average " + (attempts * 1.0 / successes);
	}
}
