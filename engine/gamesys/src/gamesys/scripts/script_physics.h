#ifndef DM_GAMESYS_SCRIPT_PHYSICS_H
#define DM_GAMESYS_SCRIPT_PHYSICS_H

#include <dlib/configfile.h>

#include <gameobject/gameobject.h>

#include <render/render.h>

namespace dmGameSystem
{
    void ScriptPhysicsRegister(const ScriptLibContext& context);
    void ScriptPhysicsFinalize(const ScriptLibContext& context);
}

#endif // DM_GAMESYS_SCRIPT_PHYSICS_H
