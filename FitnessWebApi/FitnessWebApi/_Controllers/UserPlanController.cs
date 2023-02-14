using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace FitnessWebApi._Controllers
{
	[Route("api/[controller]")]
	[ApiController]
	public class UserPlanController : ControllerBase
	{
		private readonly UserPlanService _service;

		public UserPlanController(UserPlanService service)
		{
			_service = service;
		}

		[HttpGet]
		public async Task<IActionResult> GetAll()
		{
			try
			{
				List<StaticUserPlanResponse> userPlans = await _service.GetAll();
				if(userPlans == null)
				{
					return Problem("Nothing was returned from service, this was unexpected");
				}
				
				if(userPlans.Count == 0)
				{
					return NoContent();
				}

				return Ok(userPlans);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpGet("{id}")]
		public async Task<IActionResult> GetById(int id)
		{
			try
			{
				DirectUserPlanResponse userPlan = await _service.GetById(id);
				if(userPlan == null)
				{
					return NotFound();
				}

				return Ok(userPlan);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPost]
		public async Task<IActionResult> Create([FromBody] UserPlanRequest request)
		{
			try
			{
				DirectUserPlanResponse userPlan = await _service.Create(request);
				if (userPlan == null)
				{
					return BadRequest();
				}

				return Ok(userPlan);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPut("{id}")]
		public async Task<IActionResult> Update(int id, [FromBody] UserPlanRequest request)
		{
			try
			{
				DirectUserPlanResponse userPlan = await _service.Update(id, request);
				if (userPlan == null)
				{
					return BadRequest();
				}

				return Ok(userPlan);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpDelete("{id}")]
		public async Task<IActionResult> Delete(int id)
		{
			try
			{
				DirectUserPlanResponse userPlan = await _service.Delete(id);
				if(userPlan == null)
				{
					return BadRequest();
				}

				return Ok(userPlan);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}
	}
}
