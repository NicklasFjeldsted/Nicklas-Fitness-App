using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace FitnessWebApi._Controllers
{
	[Route("api/[controller]")]
	[ApiController]
	public class UserRecipeController : ControllerBase
	{
		private readonly IUserRecipeService _service;

		public UserRecipeController(IUserRecipeService service)
		{
			_service = service;
		}

		[HttpGet("{userId}")]
		public async Task<IActionResult> GetAllById(int userId)
		{
			try
			{
				List<StaticUserRecipeResponse> recipes = await _service.GetAllById(userId);
				if (recipes == null)
				{
					return NotFound();
				}

				if(recipes.Count == 0)
				{
					return NoContent();
				}

				return Ok(recipes);
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
				DirectUserRecipeResponse recipe = await _service.GetById(id);
				if (recipe == null)
				{
					return NotFound();
				}

				return Ok(recipe);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPost]
		public async Task<IActionResult> Create([FromBody] UserRecipeRequest request)
		{
			try
			{
				DirectUserRecipeResponse recipe = await _service.Create(request);
				if (recipe == null)
				{
					return BadRequest();
				}

				return Ok(recipe);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPut("{id}")]
		public async Task<IActionResult> Update(int id, [FromBody] UserRecipeRequest request)
		{
			try
			{
				DirectUserRecipeResponse recipe = await _service.Update(id, request);
				if (recipe == null)
				{
					return NotFound();
				}

				return Ok(recipe);
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
				DirectUserRecipeResponse recipe = await _service.Delete(id);
				if (recipe == null)
				{
					return NotFound();
				}

				return Ok(recipe);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}
	}
}
